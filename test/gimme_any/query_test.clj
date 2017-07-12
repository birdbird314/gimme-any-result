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

(deftest yahoo-query-test
  (testing "Should return empty string for empty string"
    (is (empty? (yahoo-query ""))))
	
  (testing "Should create yahoo query"
    (is (= "https://search.yahoo.com/search;?p=foo" (yahoo-query "foo")))))
