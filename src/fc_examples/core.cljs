(ns fc-examples.core
  (:require [full-control.core :as fc :refer-macros [defpage]]
            [clerk.core :as cl :refer-macros [defcom-route defrouter]]
            [fc-examples.data :refer [app-state]]
            [fc-examples.common :as cm]
            [fc-examples.grids :as g :refer [grids]]
            [fc-examples.modals :as m :refer [modals]]
            [fc-examples.forms :as f :refer [forms]]))

(enable-console-print!)

(defpage home [cursor owner]
  (render-state [_]
                (fc/build cm/main-menu cursor)
                (fixed-layout
                 (row
                  (column-12
                   (panel (header (title3 "Welcome"))
                          (p "Just welcome...")))))))

(defcom-route "/" [] home)
(defcom-route "/grids" [] grids)
(defcom-route "/modals/methods" [] modals {:opts {:section m/modal-methods}})
(defcom-route "/modals/events" [] modals {:opts {:section m/modal-events}})
(defcom-route "/forms/layouts" [] forms {:opts {:section f/layouts}})
(defcom-route "/forms/state" [] forms {:opts {:section f/state}})

(defrouter my-router app-state (. js/document (getElementById "app")))

(cl/start my-router "/")
