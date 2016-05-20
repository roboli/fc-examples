(defproject fc-examples "0.1.0-SNAPSHOT"
  :description "Live examples for full-control"
  :url "http://roboli.space/full-control"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-3058"]
                 [full-control "0.1.0-SNAPSHOT"]
                 [org.clojars.roboli/clerk "0.1.0-SNAPSHOT"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]]

  :cljsbuild {:builds {:dev {:source-paths ["src"]
                             :compiler {:output-to "app/out/fc_examples.js"
                                        :output-dir "app/out"
                                        :optimizations :none
                                        :asset-path "out"
                                        :main fc_examples.core
                                        :source-map true}}
                       :prod {:source-paths ["src"]
                              :compiler {:output-to "app/out/fc_examples.js"
                                         :main fc_examples.core
                                         :externs ["externs/bootstrap-externs.js"
                                                   "externs/jquery-externs.js"
                                                   "externs/jquery-ui-externs.js"]
                                         :optimizations :advanced
                                         :pretty-print false}}}}

  :clean-targets ["app/out"])
