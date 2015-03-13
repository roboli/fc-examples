(ns fc-examples.forms
  (:require [full-control.core :as fc :refer-macros [defpage defrow]]
            [fc-examples.common :refer [main-menu]]))

(defrow layouts [cursor owner opts]
  (render-state [_]
                (with-attrs {:column-size :md}
                  (column-12
                   (row
                    (column-12
                     (panel
                      (header (title3 "Normal"))
                      (frm
                       (with-record (:item cursor)
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
                           (group-for :brand-id
                                      (lbl "Brand")
                                      (dropdown
                                       (with-source [data (:brands cursor)]
                                         (option {:value (:id data)} (:name data))))
                                      (help "*")))
                          (column-6
                           (group-for :comments
                                      (lbl)
                                      (txtarea)
                                      (help "(optional)"))))
                         (row
                          (column-6
                           (group-for :active
                                      (checkbox))))
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
                           (checkbox-inline-for [:extras :allow-discounts])))
                         (row
                          (column-6
                           (group-for :type
                                      (lbl)
                                      (radio {:value "1"} "Service")
                                      (radio {:value "2"} "Asset")))
                          (column-6
                           (group-for :type
                                      (lbl "Type Inline")
                                      (br)
                                      (radio-inline {:value "1"
                                                     :name "type1"} "Service")
                                      (radio-inline {:value "2"
                                                     :name "type1"} "Asset")))))))))
                   (row
                    (column-12
                     (panel
                      (header (title3 "Horizontal"))
                      (frm-horizontal
                       (with-record (:item cursor)
                         (row
                          (column-6
                           (group-for :description
                                      (lbl-4)
                                      (txt-6)
                                      (help-2 "*")))
                          (column-6
                           (group-for :price
                                      (lbl-4)
                                      (txt-6 {:max-length 10})
                                      (help-2 "*"))))
                         (row
                          (column-6
                           (group-for :brand-id
                                      (lbl-4 "Brand")
                                      (dropdown-6
                                       (with-source [data (:brands cursor)]
                                         (option {:value (:id data)} (:name data))))
                                      (help-2 "*")))
                          (column-6
                           (group-for :comments
                                      (lbl-4)
                                      (txtarea-6)
                                      (help-2 "(opt)"))))
                         (row
                          (column-6
                           (group-for :active
                                      (column-4)
                                      (checkbox-6))))
                         (row
                          (column-6
                           (row
                            (lbl-4 "Extras")
                            (column-6
                             (checkbox-for [:extras :non-taxable])
                             (checkbox-for [:extras :allow-credit])
                             (checkbox-for [:extras :allow-discounts]))))
                          (column-6
                           (row
                            (lbl-4 "Extras Inline")
                            (column-6
                             (checkbox-inline-for [:extras :non-taxable] "NT")
                             (checkbox-inline-for [:extras :allow-credit] "AC")
                             (checkbox-inline-for [:extras :allow-discounts] "AD")))))
                         (row
                          (column-6
                           (group-for :type
                                      (lbl-4)
                                      (column-6
                                       (radio {:value "1"} "Service")
                                       (radio {:value "2"} "Asset"))))
                          (column-6
                           (group-for :type
                                      (lbl-4)
                                      (column-6
                                       (radio-inline {:value "1"
                                                      :name "type1"} "Service")
                                       (radio-inline {:value "2"
                                                      :name "type1"} "Asset"))))))))))
                   (row
                    (column-12
                     (panel
                      (header (title3 "Inline"))
                      (row
                       (column-12
                        (frm-inline
                         (with-record (:item cursor)
                           (group-for :description
                                      (txt {:max-length 15
                                            :placeholder "Name"}))
                           (group-for :brand-id
                                      (dropdown
                                       (with-source [data (:brands cursor)]
                                         (option {:value (:id data)} (:name data)))))
                           (group-for :price
                                      (txt {:max-length 10}))
                           (group-for :comments
                                      (txt))))))
                      (row
                       (column-12
                        (frm-inline
                         (with-record (:item cursor)
                           (group-for [:extras :non-taxable]
                                      (checkbox-inline "NT"))
                           (group-for [:extras :allow-credit]
                                      (checkbox-inline "AC"))
                           (group-for [:extras :allow-discounts]
                                      (checkbox-inline "AD"))
                           (space)
                           (space)
                           (group-for :type
                                      (radio-inline {:value "1"} "Service")
                                      (radio-inline {:value "2"} "Asset")))))))))))))

(defrow state [cursor owner opts]
  (init-state []
              {:disabled false
               :description {:val-st nil
                             :disabled false}
               :price {:val-st nil
                       :disabled false}
               :brand-id {:val-st nil
                          :disabled false}
               :comments {:val-st nil
                          :disabled false}
               :extras {:val-st nil
                        :disabled false}
               :type {:val-st nil
                      :disabled false}})
  
  (render-state [st]
                (with-attrs {:column-size :md}
                  (column-4
                   (panel
                    (header (title3 "Local State"))
                    (with-controls
                      (let [val-sts [{:val nil :txt ""}
                                     {:val :has-success :txt "Success"}
                                     {:val :has-warning :txt "Warning"}
                                     {:val :has-error :txt "Error"}]]
                        (frm-horizontal
                         (with-record st
                           (row
                            (lbl-4 {:size :sm} "Form")
                            (checkbox-6-for {:korks :disabled
                                             :size :sm}))
                           (row
                            (column-12
                             (group-for {:korks [:description :val-st]
                                         :size :sm}
                                        (lbl-4 "Description")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:description :disabled]
                                               :size :sm}))))
                           (row
                            (column-12
                             (group-for {:korks [:price :val-st]
                                         :size :sm}
                                        (lbl-4 "Price")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:price :disabled]
                                               :size :sm}))))
                           (row
                            (column-12
                             (group-for {:korks [:brand-id :val-st]
                                         :size :sm}
                                        (lbl-4 "Brands")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:brand-id :disabled]
                                               :size :sm}))))
                           (row
                            (column-12
                             (group-for {:korks [:comments :val-st]
                                         :size :sm}
                                        (lbl-4 "Comments")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:comments :disabled]
                                               :size :sm}))))
                           (row
                            (column-12
                             (group-for {:korks [:extras :val-st]
                                         :size :sm}
                                        (lbl-4 "Extras")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:extras :disabled]
                                               :size :sm}))))
                           (row
                            (column-12
                             (group-for {:korks [:type :val-st]
                                         :size :sm}
                                        (lbl-4 "Type")
                                        (dropdown-6
                                         (with-source [data val-sts]
                                           (option {:value (:val data)} (:txt data)))))
                             (row
                              (column-4)
                              (checkbox-6-for {:korks [:type :disabled]
                                               :size :sm}))))))))))
                  (column-8
                   (panel
                    (header (title3 "Form"))
                    (frm {:disabled (:disabled st)}
                         (with-record (:item cursor)
                           (row
                            (column-6
                             (group-for {:korks :description
                                         :validation-state (keyword (get-in st [:description :val-st]))}
                                        (lbl)
                                        (txt {:max-length 15
                                              :disabled (get-in st [:description :disabled])})
                                        (help "*")))
                            (column-6
                             (group-for {:korks :price
                                         :validation-state (keyword (get-in st [:price :val-st]))}
                                        (lbl)
                                        (txt {:max-length 10
                                              :disabled (get-in st [:price :disabled])})
                                        (help "*"))))
                           (row
                            (column-6
                             (group-for {:korks :brand-id
                                         :validation-state (keyword (get-in st [:brand-id :val-st]))}
                                        (lbl "Brand")
                                        (dropdown {:disabled (get-in st [:brand-id :disabled])}
                                                  (with-source [data (:brands cursor)]
                                                    (option {:value (:id data)} (:name data))))
                                        (help "*")))
                            (column-6
                             (group-for {:korks :comments
                                         :validation-state (keyword (get-in st [:comments :val-st]))}
                                        (lbl)
                                        (txtarea {:disabled (get-in st [:comments :disabled])})
                                        (help "(optional)"))))
                           (row
                            (column-6 {:validation-state (keyword (get-in st [:extras :val-st]))}
                                      (lbl "Extras")
                                      (checkbox-for {:korks [:extras :non-taxable]
                                                     :disabled (get-in st [:extras :disabled])})
                                      (checkbox-for {:korks [:extras :allow-credit]
                                                     :disabled (get-in st [:extras :disabled])})
                                      (checkbox-for {:korks [:extras :allow-discounts]
                                                     :disabled (get-in st [:extras :disabled])}))
                            (column-6
                             (group-for {:korks :type
                                         :validation-state (keyword (get-in st [:type :val-st]))}
                                        (lbl)
                                        (radio {:value "1"
                                                :disabled (get-in st [:type :disabled])} "Service")
                                        (radio {:value "2"
                                                :disabled (get-in st [:type :disabled])} "Asset")))))))))))

(defpage forms [cursor owner opts]
  (render-state [st]
                (fc/build main-menu cursor)
                (fixed-layout
                 (row
                  (column-9
                   (fc/build (:section opts) (:forms cursor)))
                  (column-3
                   (navpanel (header (title3 "Forms"))
                             (link {:href "#/forms/layouts"} "Layouts")
                             (link {:href "#/forms/state"} "State")))))))
