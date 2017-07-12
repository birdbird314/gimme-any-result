(ns gimme-any.parse-test
  (:require [clojure.test :refer :all]
            [gimme-any.parse :refer :all]))

(def resp-with-one-res 
"<html>
<a href=\"someNoise\" more noise =+_0-9432_() 
data-href=\"someUrl\">Some description</a>
</html>")

(def resp-with-multiple-res 
"<html>
<a href=\"someNoise\" more noise =+_0-9432_() 
data-href=\"someUrl1\">Some description</a>
SOME MORE NOISE *&)*(&#$*(@#SDF:J:SDF:J
<a href=\"someNoise\" more noise =+_0-9432_() 
data-href=\"someUrl2\">Some description</a>
</html>")


(deftest parse-google-test
  (testing "Should return empty seq for empty input"
    (is (empty? (parse-google ""))))
	
  (testing "Should parse response with one result"
    (is (= '("someUrl") (parse-google resp-with-one-res))))
	
  (testing "Should parse response with multiple results"
    (is (= '("someUrl1" "someUrl2") (parse-google resp-with-multiple-res)))))	
