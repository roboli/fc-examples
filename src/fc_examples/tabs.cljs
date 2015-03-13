(ns fc-examples.tabs
  (:refer-clojure :exclude [methods])
  (:require [full-control.core :as fc :refer-macros [defpage defpanel]]
            [full-control.behaviors :as b]
            [fc-examples.common :refer [main-menu]]))

(defpanel methods [cursor owner]
  (render-state [st]
                (header "Methods")
                (row
                 (column-12
                  (p "Activate tabs using methods.")))
                (row
                 (column-6
                  (form {:class-name "form-horizontal"}
                        (group
                         (lbl-1 "Tab")
                         (dropdown-5 {:on-change #(b/nav-tab-activate "form-tabs"
                                                                      (.. % -target -value))
                                      :defaultValue "tab-2"}
                                     (option {:value "tab-1"} "Texts")
                                     (option {:value "tab-2"} "Checkboxes")
                                     (option {:value "tab-3"} "Dropdown"))))))
                (br)
                (frm
                 (with-record (:item cursor)
                   (row
                    (column-12
                     (nav-tabs {:id "form-tabs"}
                               (nav-tab {:id "tab-1"}
                                        (tab "Texts")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (group-for :description
                                                      (lbl)
                                                      (txt {:max-length 15})
                                                      (help "*")))
                                          (column-6
                                           (group-for :price
                                                      (lbl)
                                                      (txt {:max-length 10})
                                                      (help "*"))))
                                         (row
                                          (column-6
                                           (group-for :comments
                                                      (lbl)
                                                      (txtarea)
                                                      (help "(optional)"))))))
                               (nav-tab {:id "tab-2"
                                         :active true}
                                        (tab "Checkboxes")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (lbl "Extras")
                                           (checkbox-for [:extras :non-taxable])
                                           (checkbox-for [:extras :allow-credit])
                                           (checkbox-for [:extras :allow-discounts]))
                                          (column-6
                                           (lbl "Extras Inline")
                                           (br)
                                           (checkbox-inline-for [:extras :non-taxable])
                                           (checkbox-inline-for [:extras :allow-credit])
                                           (checkbox-inline-for [:extras :allow-discounts])))))
                               (nav-tab {:id "tab-3"}
                                        (tab "Dropdown")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (group-for :brand-id
                                                      (lbl "Brand")
                                                      (dropdown
                                                       (with-source [data (:brands cursor)]
                                                         (option {:value (:id data)} (:name data))))
                                                      (help "*")))))))))))))

(defpanel events [cursor owner]
  (init-state []
              {:tab-id "tab-2"
               :tabs-chs (b/init-chans)
               :event ""})

  (will-mount []
              (b/listen :tab-show
                        (fc/get-state owner [:tabs-chs :pub])
                        (b/nav-tab-handler (fn [_ id _]
                                             (fc/update-state! owner :event #(str % " Show-" id))
                                             (fc/set-state! owner :tab-id id))))
              (b/nav-tab-on-event :on-show :tab-show 
                                  (fc/get-state owner [:tabs-chs :ch])
                                  "form-tabs")

              (b/listen :tab-shown
                        (fc/get-state owner [:tabs-chs :pub])
                        (b/nav-tab-handler (fn [_ id _]
                                             (fc/update-state! owner :event #(str % " Shown-" id))
                                             (fc/set-state! owner :tab-id id))))
              (b/nav-tab-on-event :on-shown :tab-shown 
                                  (fc/get-state owner [:tabs-chs :ch])
                                  "form-tabs")

              (b/listen :tab-hide
                        (fc/get-state owner [:tabs-chs :pub])
                        (b/nav-tab-handler (fn [_ id _]
                                             (fc/update-state! owner :event #(str % " Hide-" id))
                                             (fc/set-state! owner :tab-id id))))
              (b/nav-tab-on-event :on-hide :tab-hide 
                                  (fc/get-state owner [:tabs-chs :ch])
                                  "form-tabs")

              (b/listen :tab-hidden
                        (fc/get-state owner [:tabs-chs :pub])
                        (b/nav-tab-handler (fn [_ id _]
                                             (fc/update-state! owner :event #(str % " Hidden-" id)))))
              (b/nav-tab-on-event :on-hidden :tab-hidden
                                  (fc/get-state owner [:tabs-chs :ch])
                                  "form-tabs"))

  (render-state [st]
                (header "Events")
                (row
                 (column-12
                  (p "Activate tabs using state. And keep track through events when clicking on tabs.")))
                (frm-horizontal
                 (with-record st
                   (row
                    (column-6
                     (group-for :tab-id
                                (lbl-1 "Tab")
                                (dropdown-5
                                 (with-source [data [{:id "tab-1" :name "Texts"}
                                                     {:id "tab-2" :name "Checkboxes"}
                                                     {:id "tab-3" :name "Dropdown"}]]
                                   (option {:value (:id data)} (:name data))))))
                    (column-6
                     (p (:event st))))))
                (br)
                (frm
                 (with-record (:item cursor)
                   (row
                    (column-12
                     (nav-tabs {:id "form-tabs"}
                               (nav-tab {:id "tab-1"
                                         :active (= "tab-1" (:tab-id st))}
                                        (tab "Texts")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (group-for :description
                                                      (lbl)
                                                      (txt {:max-length 15})
                                                      (help "*")))
                                          (column-6
                                           (group-for :price
                                                      (lbl)
                                                      (txt {:max-length 10})
                                                      (help "*"))))
                                         (row
                                          (column-6
                                           (group-for :comments
                                                      (lbl)
                                                      (txtarea)
                                                      (help "(optional)"))))))
                               (nav-tab {:id "tab-2"
                                         :active (= "tab-2" (:tab-id st))}
                                        (tab "Checkboxes")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (lbl "Extras")
                                           (checkbox-for [:extras :non-taxable])
                                           (checkbox-for [:extras :allow-credit])
                                           (checkbox-for [:extras :allow-discounts]))
                                          (column-6
                                           (lbl "Extras Inline")
                                           (br)
                                           (checkbox-inline-for [:extras :non-taxable])
                                           (checkbox-inline-for [:extras :allow-credit])
                                           (checkbox-inline-for [:extras :allow-discounts])))))
                               (nav-tab {:id "tab-3"
                                         :active (= "tab-3" (:tab-id st))}
                                        (tab "Dropdown")
                                        (tab-pane
                                         (br)
                                         (row
                                          (column-6
                                           (group-for :brand-id
                                                      (lbl "Brand")
                                                      (dropdown
                                                       (with-source [data (:brands cursor)]
                                                         (option {:value (:id data)} (:name data))))
                                                      (help "*")))))))))))))

(defpage tabs [cursor owner opts]
  (render-state [st]
                (fc/build main-menu cursor)
                (fixed-layout
                 (row
                  (column-9
                   (fc/build (:section opts) (:forms cursor)))
                  (column-3
                   (navpanel (header (title3 "Tabs"))
                             (link {:href "#/tabs/methods"} "Methods")
                             (link {:href "#/tabs/events"} "Events")))))))
