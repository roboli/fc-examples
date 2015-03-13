(ns fc-examples.common
  (:require [full-control.core :as fc :refer-macros [defnavbar]]))

(defnavbar main-menu [cursor owner opts]
  (render-state [_]
                (brand "full-control")
                (link {:href "#/"} "Home")
                (link {:href "#/grids"} "Grids")
                (link {:href "#/modals/methods"} "Modals")
                (link {:href "#/forms/layouts"} "Forms")
                (link {:href "#/tabs/methods"} "Tabs")
                (link {:href "#/pagers"} "Pager")))
