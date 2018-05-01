(require '[figwheel-sidecar.repl-api :as fig])

(def compiler-config {:main          'cljs-sc-test.core
                      :output-to     "target/cljs_sc_test/main.js"
                      :output-dir    "target/cljs_sc_test/main"
                      :target        :nodejs
                      :optimizations :none
                      :source-map    true})

(fig/start-figwheel!
 {:figwheel-options {:nrepl-port       7899
                     :nrepl-middleware ["cider.nrepl/cider-middleware"
                                        "refactor-nrepl.middleware/wrap-refactor"
                                        "cemerick.piggieback/wrap-cljs-repl"]}
  :all-builds       [{:id           "dev"
                      :figwheel     true
                      :source-paths ["src"]
                      :compiler     compiler-config}]})
