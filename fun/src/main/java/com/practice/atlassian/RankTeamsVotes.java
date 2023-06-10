package com.practice.atlassian;

import java.util.Arrays;

public class RankTeamsVotes {

  public String rankTeams(String[] votes) {
    // ["ABC","ACB","ABC","ACB","ACB"]
    /*
      5 0 0 A
      0 2 3 B
      0 3 2 C
     */
    final int n = votes[0].length();
    final int[][] count = new int[26][n+ 1];
    for (int i = 0; i < 26; i++) {
      count[i][n] = i;
    }
    for (final String vote : votes) {
      for (int i = 0; i < vote.length(); i++) {
        final int idx = vote.charAt(i) - 'A';
        count[idx][i]++;
      }
    }
    Arrays.sort(count, (a, b) -> {
      for (int i = 0; i < n; i++) {
        if(a[i]  < b[i]){
          return 1;
        }
        if (a[i] > b[i]) {
          return -1;
        }
      }
      return 0;
    });
    String result = "";
    for (int i = 0; i < n; i++) {
      result += ((char) ('A' + count[i][n]));
    }
    return result;
  }
}
