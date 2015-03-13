(ns fc-examples.pagers
  (:require [full-control.core :as fc :refer-macros [defpage]]
            [fc-examples.common :refer [main-menu]]))

(defpage pagers [cursor owner opts]
  (render-state [st]
                (fc/build main-menu cursor)
                (fixed-layout
                 (row
                  (column-12
                   (panel (header "Pager")
                          (stretch
                           (grid
                            (thead
                             (th "Id")
                             (th "City"))
                            (tbody
                             (source [data (let [pagination (get-in cursor [:pagers :pagination])
                                                 page (:page pagination)
                                                 page-size (:page-size pagination)]
                                             (take page-size
                                                   (drop (* (dec page) page-size)
                                                         (get-in cursor [:pagers :cities]))))]
                                     (td (:id data))
                                     (td (:name data)))))
                           (space)
                           (space)
                           (pager {:page (get-in cursor [:pagers :pagination :page])
                                   :page-size (get-in cursor [:pagers :pagination :page-size])
                                   :total-pages (get-in cursor [:pagers :pagination :total-pages])
                                   :pager-size 2
                                   :page-sizes [5 10 15]
                                   :on-page-changed #(fc/update! cursor [:pagers :pagination :page] %)
                                   :on-page-size-changed (fn [v]
                                                           (let [pagination (get-in @cursor [:pagers :pagination])
                                                                 total-pages (Math/ceil (/ (:total-records pagination) v))
                                                                 page (:page pagination)]
                                                             (fc/update! cursor [:pagers :pagination :page-size] v)
                                                             (fc/update! cursor [:pagers :pagination :total-pages] total-pages)
                                                             (fc/update! cursor
                                                                         [:pagers :pagination :page]
                                                                         (if (> page total-pages) total-pages page))))}))))))))
