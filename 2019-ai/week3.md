Week 3 Assignments 
--

Read this paper and answer the following questions based on it.
https://dl.packetstormsecurity.net/papers/IDS/hids/Learning-to-Detect-Malicious-Executables-in-the-Wild.pdf 

There are two versions of this paper: a shorter conference version with double columns, and a longer journal version. 


1.  Answer the following questions from the assigned paper.
    (a) What is a relevant feature, and how did this paper determine which features are "relevant?"  Do you agree with this?  Why?

    (b) For a binary classification, a false positive (FP) is a negative instance incorrectly classified positively, 
    a true positive (TP) is a correctly classified positive instance, a false negative (FN) is an incorrectly classified negative instance, and a true negative (TN) is a correctly classified negative instance.
    
    For example, if a patient has a disease and is tested for it (by a doctor or a classifier), the result of the test are as follows:
    * TP = correctly diagnosed as having the deases
    * FP = incorrectly diagnosed as having the disease
    * TN = correctly diagnosed as not having the disease
    * FN = incorrectly diagnosed as not having the disease.
    
 
 2.   In many problem domains, we use two different metrics to give is a different sense of how well a model performs: precision and recall.
    **Precision** measures how likely a model's positive predictions are to be correct: 
    TP / (TP + FP) = # correctly classified true examples / # examples model classified as true .  If we have too many false positives and not enough true positives, this score decreases.  We can trivially maximize this by by never guessing true, i.e., always guessing -1.  (For simplicity, say that if the denominator is 0, the precision is 1.)  
    
 Do TP and FP have meanings in a multiclass setting?
    
    
