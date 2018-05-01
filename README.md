## Testing scope capture with Cljs


### Requirements

`clj` command line tool, node and optionnaly yarn.

`yarn install` or `npm install` (we need to install the `ws` websocket package for figwheel).

### Steps

- In one terminal window `clj script/cljs-vanilla-repl.cljs`. Notice the cljs compilation warnings:

```
Figwheel: Starting server at http://0.0.0.0:3449
Figwheel: Watching build - dev
Figwheel: Cleaning build - dev
Compiling build :dev to "target/cljs_sc_test/main.js" from ["src"]...
WARNING: Use of undeclared Var sc.impl/*file* at line 74 target/cljs_sc_test/main/sc/impl.cljc
WARNING: Use of undeclared Var sc.impl/Throwable at line 114 target/cljs_sc_test/main/sc/impl.cljc
SPY <-1> /home/chpill/p/clj/buf/cljs-sc-test/script/cljs-vanilla-repl.clj:10 
 At Code Site -1, will save scope with locals [x a b c]
Successfully compiled build :dev to "target/cljs_sc_test/main.js" in 6.178 seconds.
Launching ClojureScript REPL for build: dev
Figwheel Controls:
          (stop-autobuild)                ;; stops Figwheel autobuilder
          (start-autobuild id ...)        ;; starts autobuilder focused on optional ids
          (switch-to-build id ...)        ;; switches autobuilder to different build
          (reset-autobuild)               ;; stops, cleans, and starts autobuilder
          (reload-config)                 ;; reloads build config and resets autobuild
          (build-once id ...)             ;; builds source one time
          (clean-builds id ..)            ;; deletes compiled cljs target files
          (print-config id ...)           ;; prints out build configurations
          (fig-status)                    ;; displays current state of system
          (figwheel.client/set-autoload false)    ;; will turn autoloading off
          (figwheel.client/set-repl-pprint false) ;; will turn pretty printing off
  Switch REPL build focus:
          :cljs/quit                      ;; allows you to switch REPL to another build
    Docs: (doc function-name-here)
    Exit: :cljs/quit
 Results: Stored in vars *1, *2, *3, *e holds last exception object
Prompt will show when Figwheel connects to your application
```

- In another terminal window `node target/cljs_sc_test/main.js`. This gives this output:

```
SPY [1 -1] /home/chpill/p/clj/buf/cljs-sc-test/bin/cljs.clj:10 
  At Execution Point 1 of Code Site -1, saved scope with locals [x a b c]
SPY [1 -1] /home/chpill/p/clj/buf/cljs-sc-test/bin/cljs.clj:10 
(+ a b c)
=>
36
Figwheel: trying to open cljs reload socket
Figwheel: socket connection established
```


- Back in the figwheel cljs repl window, type the following

```
dev:cljs.user=> (require '[sc.api :as sc])
nil
dev:cljs.user=> (sc/ep-info 1)
#:sc.ep{:id 1,
        :code-site
        #:sc.cs{:id -1,
                :expr (+ a b c),
                :local-names [x a b c],
                :dynamic-var-names nil,
                :file
                "/home/chpill/p/clj/buf/cljs-sc-test/script/cljs-vanilla-repl.clj",
                :line 10,
                :column 5},
        :local-bindings {x 4, a 8, b 12, c 16},
        :dynamic-var-bindings {}, 
        :value 36}
dev:cljs.user=> (sc/letsc 1 a)
----  Could not Analyze  <cljs form>   line:1  column:1  ----

  No Execution Point with ID 1

  1  (sc/letsc 1 a)
     ^--- 

----  Analysis Error  ----
nil

```


