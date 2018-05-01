(require '[figwheel-sidecar.repl-api :as fig])

(def compiler-config {:main          'cljs-sc-test.core
                      :output-to     "target/cljs_sc_test/main.js"
                      :output-dir    "target/cljs_sc_test/main"
                      :target        :nodejs
                      :optimizations :none
                      :source-map    true})

(fig/start-figwheel!
 {:all-builds       [{:id           "dev"
                      :figwheel     true
                      :source-paths ["src"]
                      :compiler     compiler-config}]})


(fig/cljs-repl)
