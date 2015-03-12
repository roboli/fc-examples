(ns fc-examples.grids
  (:require [full-control.core :as fc :refer-macros [deffixed-layout]]))

(deffixed-layout grids [cursor owner opts]
  (render-state [st]
                (row
                 (column-12
                  (panel
                   (header (title1 "Grid-View"))
                   (p "Some data here...")
                   (stretch
                    (grid-view
                     (with-source [data (:items cursor)]
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
                      (with-source [data (:items cursor)]
                        (td (:description data))
                        (td (:price data))
                        (td (:uom data))
                        (td (:image-url data)))))))))))
