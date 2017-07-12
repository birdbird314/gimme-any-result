(ns gimme-any.parse)

(defn parse-google
  [response]
  (->> response 
    (re-seq #"data-href=\"([^\"]*)\"")
	(map second)))
