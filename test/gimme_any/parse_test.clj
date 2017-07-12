(ns gimme-any.parse-test
  (:require [clojure.test :refer :all]
            [gimme-any.parse :refer :all]))

(def google-resp-with-one-res 
"<html>
<a href=\"someNoise\" more noise =+_0-9432_() 
data-href=\"someUrl\">Some description</a>
</html>")

(def google-resp-with-empty-url 
"<html>
<a href=\"someNoise\" more noise =+_0-9432_() 
data-href=\"\">Some description</a>
</html>")


(def google-resp-with-multiple-res 
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
    (is (= '("someUrl") (parse-google google-resp-with-one-res))))
	
  (testing "Should parse response with multiple results"
    (is (= '("someUrl1" "someUrl2") (parse-google google-resp-with-multiple-res))))
	
  (testing "Should ignore empty url's"
    (is (empty? (parse-google google-resp-with-empty-url))))
	
	
	)	

(def bing-resp-with-one-res 
"<html>
<h2>
  <a href=\"someUrl\" h=\"noise\">
    NOISE <>>KLJSDF)(*&*(&
  </a>
</h2>
</html>")

(def bing-resp-with-empty-url 
"<html>
<h2>
  <a href=\"\" h=\"noise\">
    NOISE <>>KLJSDF)(*&*(&
  </a>
</h2>
</html>")

(def bing-resp-with-multiple-res 
"<html>
<h2>
  <a href=\"someUrl1\" h=\"noise\">
    NOISE <>>KLJSDF)(*&*(&
  </a>
</h2>
NOISE ()*)*()*(&*
<h2>
  <a href=\"someUrl2\" h=\"noise\">
    NOISE <>>KLJSDF)(*&*(&
  </a>
</h2>
</html>")


(deftest parse-bing-test
  (testing "Should return empty seq for empty input"
    (is (empty? (parse-bing ""))))
	
  (testing "Should parse response with one result"
    (is (= '("someUrl") (parse-bing bing-resp-with-one-res))))
	
  (testing "Should parse response with multiple results"
    (is (= '("someUrl1" "someUrl2") (parse-bing bing-resp-with-multiple-res))))
	
  (testing "Should ignore empty url's"
    (is (empty? (parse-bing bing-resp-with-empty-url)))))	

