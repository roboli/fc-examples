(ns fc-examples.core
  (:require [full-control.core :as fc :refer-macros [defpage deffixed-layout]]
            [clerk.core :as c :refer-macros [defcom-route defrouter]]
            [fc-examples.data :refer [app-state]]
            [fc-examples.grids :as g :refer [grids]]))

(enable-console-print!)

(deffixed-layout home [cursor owner]
  (render-state [_]
                (row
                 (column-12
                  (panel (header (title3 "Welcome"))
                         (p "Just welcome..."))))))

(defpage master [cursor owner opts]
  (render-state [_]
                (navbar (brand "full-control")
                        (link {:href "#/"} "Home")
                        (link {:href "#/grids"} "Grids"))
                (fc/build (:view opts) ((:data-key opts) cursor))))

(defcom-route "/" [] master {:opts {:view home
                                    :data-key identity}})
(defcom-route "/grids" [] master {:opts {:view grids
                                         :data-key :grids}})

(defrouter my-router app-state (. js/document (getElementById "app")))

(c/start my-router "/")
