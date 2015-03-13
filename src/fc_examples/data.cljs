(ns fc-examples.data)

(def app-state (atom {:grids {:items [{:description "iPod"
                                       :price 150
                                       :uom "unit"
                                       :image-url "ipod.jpeg"}
                                      {:description "iMac"
                                       :price 999
                                       :uom "unit"
                                       :image-url "imac.jpeg"}
                                      {:description "iPhone"
                                       :price 550
                                       :uom "unit"
                                       :image-url "iphone.jpeg"}]}

                      :modals {:msg ""}

                      :forms {:brands [{:id 1 :name "Hermex"}
                                       {:id 2 :name "Stanley"}]
                              :item {:description "Screw Driver"
                                     :brand-id 2
                                     :price 44.5
                                     :active true
                                     :comments "Yellow color plastic."
                                     :extras {:non-taxable false
                                              :allow-credit false
                                              :allow-discounts false}
                                     :type "1"}
                              :state {:disabled false}}}))
