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
                              :state {:disabled false}}

                      :pagers {:cities [{:id 1 :name "Guatemala City"}
                                        {:id 2 :name "San Jose"}
                                        {:id 3 :name "San Salvador"}
                                        {:id 4 :name "Panama City"}
                                        {:id 5 :name "Tegucigalpa"}
                                        {:id 6 :name "Managua"}
                                        {:id 7 :name "Belmopan"}
                                        {:id 8 :name "Mexico City"}
                                        {:id 9 :name "Bogota"}
                                        {:id 10 :name "Quito"}
                                        {:id 11 :name "Caracas"}
                                        {:id 12 :name "Lima"}
                                        {:id 13 :name "Sucre"}]
                               :pagination {:page 1
                                            :page-size 10
                                            :total-pages 2
                                            :total-records 13}}}))
