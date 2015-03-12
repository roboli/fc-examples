(ns fc-examples.modals
  (:require [full-control.core :as fc :refer-macros [defpage
                                                     defpanel
                                                     defmodal]]
            [full-control.behaviors :as b]
            [fc-examples.common :as cm]))

(defmodal msg-modal [cursor owner opts]
  (render-state [st]
                (with-attrs {:id "msg-modal"}
                  (header (title3 "Msg Modal"))
                  (p "Please click a button: ")
                  (footer (btn {:on-click (fn [_]
                                            (b/modal-display :hide "msg-modal")
                                            (fc/update! cursor :msg "It's okay!"))}
                               "Ok")
                          (btn {:on-click (fn [_]
                                            (b/modal-display :hide "msg-modal")
                                            (fc/update! cursor :msg "Not okay..."))}
                               "Cancel")))))

(defpanel modal-methods [cursor owner]
  (render-state [st]
                ;; inline modal
                (modal {:id "my-modal"}
                       (header (title3 "My Modal"))
                       (p "Hello Modal!")
                       (footer (btn {:on-click #(b/modal-display :hide "my-modal")}
                                    "Close")))
                ;; component modal
                (fc/build msg-modal cursor {:opts {:ch (get-in st [:modal-msg-chs :ch])}})
                (header (title3 "Methods"))
                (row
                 (column-6
                  (btn {:on-click #(b/modal-display :show "my-modal")}
                       "Open-1"))
                 (column-6
                  (row
                   (column-6
                    (btn {:on-click #(b/modal-display :show "msg-modal")}
                         "Open-2"))
                   (column-6
                    (p (str "Response: " (:msg cursor)))))))))

(defpanel modal-events [cursor owner]
  (init-state []
              {:modal-chs (b/init-chans)
               :event ""})

  (will-mount []
              (b/listen :show
                        (fc/get-state owner [:modal-chs :pub])
                        (b/modal-handler (fn [& _]
                                           (fc/update-state! owner :event #(str % " Show")))))
              (b/modal-on-event :on-show :show
                                (fc/get-state owner [:modal-chs :ch])
                                "my-modal")

              
              (b/listen :shown
                        (fc/get-state owner [:modal-chs :pub])
                        (b/modal-handler (fn [& _]
                                           (fc/update-state! owner :event #(str % " Shown")))))
              (b/modal-on-event :on-shown :shown
                                (fc/get-state owner [:modal-chs :ch])
                                "my-modal")

              (b/listen :hide
                        (fc/get-state owner [:modal-chs :pub])
                        (b/modal-handler (fn [& _]
                                           (fc/update-state! owner :event #(str % " Hide")))))
              (b/modal-on-event :on-hide :hide
                                (fc/get-state owner [:modal-chs :ch])
                                "my-modal")
              
              (b/listen :hidden
                        (fc/get-state owner [:modal-chs :pub])
                        (b/modal-handler (fn [& _]
                                           (fc/update-state! owner :event #(str % " Hidden")))))
              (b/modal-on-event :on-hidden :hidden
                                (fc/get-state owner [:modal-chs :ch])
                                "my-modal"))

  (render-state [st]
                (modal {:id "my-modal"}
                       (header (title3 "My Modal"))
                       (p "Hello Modal!")
                       (footer (btn {:on-click #(b/modal-display :hide "my-modal")}
                                    "Close")))
                (header (title3 "Events"))
                (row
                 (column-2
                  (btn {:on-click #(b/modal-display :show "my-modal")}
                       "Open"))
                 (column-10
                  (p (:event st))))))

(defpage modals [cursor owner opts]
  (render-state [st]
                (fc/build cm/main-menu cursor)
                (fixed-layout
                 (row
                  (column-9
                   (fc/build (:section opts) (:modals cursor)))
                  (column-3
                   (navpanel (header (title3 "Modals"))
                             (link {:href "#/modals/methods"} "Methods")
                             (link {:href "#/modals/events"} "Events")))))))
