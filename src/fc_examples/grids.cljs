(ns fc-examples.grids
  (:require [full-control.core :as fc :refer-macros [defpage]]
            [fc-examples.common :as cm]))

(defpage grids [cursor owner opts]
  (render-state [st]
                (fc/build cm/main-menu cursor)
                (fixed-layout
                 (row
                  (column-12
                   (panel
                    (header (title1 "Grid-View"))
                    (p "Some data here...")
                    (stretch
                     (grid-view
                      (with-source [data (get-in cursor [:grids :items])]
                        (row
                         (column-12
                          (h3 (:description data))))
                        (row
                         (column-8
                          (row
                           (column-4
                            (label "Price:"))
                           (column-8
                            (p (:price data))))
                          (row
                           (column-4
                            (label "U/M:"))
                           (column-8
                            (p (:uom data)))))
                         (column-4
                          (p (:image-url data))))))))
                   (panel
                    (header (title1 "Grid"))
                    (p "More data here...")
                    (stretch
                     (grid
                      (thead
                       (th "Description")
                       (th "Price")
                       (th "U/M")
                       (th "Image"))
                      (tbody
                       (with-source [data (get-in cursor [:grids :items])]
                         (td (:description data))
                         (td (:price data))
                         (td (:uom data))
                         (td (:image-url data))))))))))))
