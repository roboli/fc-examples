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

                      :modals {:msg ""}}))
