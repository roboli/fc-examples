(defproject fc-examples "0.1.0-SNAPSHOT"
  :description "Live examples for full-control"
  :url "http://roboli.space/full-control"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2755"]
                 [full-control "0.1.0-SNAPSHOT"]
                 [org.clojars.roboli/clerk "0.1.0-SNAPSHOT"]]

  :plugins [[lein-cljsbuild "1.0.4"]]

  :source-paths ["src" "target/classes"]

  :clean-targets ["app/out/fc_examples" "app/out/fc_examples.js"]

  :cljsbuild {:builds {:dev {:source-paths ["src"]
                             :compiler {:output-to "app/out/fc_examples.js"
                                        :output-dir "app/out"
                                        :optimizations :none
                                        :cache-analysis true
                                        :source-map true}}
                       :prod {:source-paths ["src"]
                              :compiler {:output-to "app/out/fc_examples.js"
                                         :optimizations :advanced}}}}

  :clean-targets ["app/out"])
