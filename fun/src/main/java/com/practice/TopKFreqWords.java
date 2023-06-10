package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFreqWords {

  public static void main(String[] args){
    final TopKFreqWords solver = new TopKFreqWords();
    {
      final String[] words = {"i","love","leetcode","i","love","coding"};
      final int k = 2;
      final List<String> results = solver.topKFrequent(words, k);
      System.out.println(results);
    }
    {
      final String[] words = {"the","day","is","sunny","the","the","the","sunny","is","is"};
      final int k = 4;
      final List<String> results = solver.topKFrequent(words, k);
      System.out.println(results);
    }
  }

  public List<String> topKFrequent(String[] words, int k) {
    final Map<String, Word> map = new HashMap<>();
    for(final String word : words) {
      final Word w  = map.computeIfAbsent(word, key -> new Word(word));
      w.inc();
    }
    final List<String> results = new ArrayList<>();
    // Min heap
    final PriorityQueue<Word> pq = new PriorityQueue<>((o1, o2) -> {
      if(o1.freq == o2.freq) {
        return o2.word.compareTo(o1.word);
      }
      return Integer.compare(o1.freq, o2.freq);
    });

    for(final Word word : map.values()) {
      pq.offer(word);
      if (pq.size() > k) {
        pq.poll();
      }
    }
    while(!pq.isEmpty()) {
      results.add(pq.poll().word);
    }
    Collections.reverse(results);
    return results;
  }

  private static class Word {
    private final String word;
    private int freq;

    Word(final String word) {
      this.word = word;
      this.freq = 0;
    }

    private void inc() {
      this.freq++;
    }

    public String toString() {
      return "(" + word + "," + freq + ")";
    }
  }
}
