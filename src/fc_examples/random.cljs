(ns fc-examples.random
  (:require [full-control.core :as fc :refer-macros [defpage defpanel]]
            [full-control.behaviors :as b]
            [fc-examples.common :refer [main-menu]]))

(defpanel date-panel [cursor owner]
  (did-mount []
             (b/jquery-datepicker "dtpk"
                                  :date-format "dd/mm/yy"
                                  :on-select #(fc/transact! cursor [:random :item :date]
                                                            (fn [_]
                                                              (fc/string->date "dd/MM/yyyy" %)))))
  
  (render-state [st]
                (header "Datepicker")
                (row
                 (column-6
                  (frm-horizontal
                   (with-record (get-in cursor [:random :item])
                     (row
                      (column-12
                       (group-for :date
                                  (lbl-2)
                                  (datepicker-6 {:id "dtpk"
                                                 :format "dd/MM/yyyy"})))))))
                 (column-6
                  (p (str (get-in cursor [:random :item :date])))))))

(defpanel autocomplete-panel [cursor owner]
  (did-mount []
             (b/jquery-autocomplete "city"
                                    (mapv #(assoc % :value (:name %)) (get-in cursor [:pagers :cities]))
                                    (fn [_ ui]
                                      (fc/update! cursor [:random :item :city] (.. ui -item -id)))
                                    (fn [item]
                                      (str
                                       (fc/render-to-str (fc/a* {} (. item -name)))))))
  
  (render-state [st]
                (header "Autocomplete")
                (row
                 (column-6
                  (form {:class-name "form-horizontal"}
                        (group
                         (lbl-2 "Search")
                         (txt-6 {:id "city"}))))
                 (column-6
                  (p (str "Id: " (get-in cursor [:random :item :city])))))))

(defpage random [cursor owner opts]
  (render-state [st]
                (fc/build main-menu cursor)
                (fixed-layout
                 (row
                  (column-12
                   (fc/build date-panel cursor)))
                 (row
                  (column-12
                   (fc/build autocomplete-panel cursor))))))
