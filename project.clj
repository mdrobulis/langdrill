(defproject lang "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/data.csv "0.1.4"]]
  :plugins [
            [lein-binplus "0.6.2"]]
    :bin {:name "german-drill"
          :bin-path "~/bin"
          :bootclasspath true}
  :main ^:skip-aot lang.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
