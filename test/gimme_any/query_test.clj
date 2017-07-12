(ns gimme-any.query-test
  (:require [clojure.test :refer :all]
            [gimme-any.query :refer :all]))

(deftest google-query-test
  (testing "Should return empty string for empty string"
    (is (empty? (google-query ""))))
	
  (testing "Should create google query"
    (is (= "https://www.google.pl/search?q=foo" (google-query "foo")))))

(deftest bing-query-test
  (testing "Should return empty string for empty string"
    (is (empty? (bing-query ""))))
	
  (testing "Should create bing query"
    (is (= "https://www.bing.com/search?q=foo" (bing-query "foo")))))
