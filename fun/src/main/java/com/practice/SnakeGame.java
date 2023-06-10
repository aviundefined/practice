package com.practice;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SnakeGame {

  final Set<Cell> food = new HashSet<>();
  final Set<Cell> snake = new HashSet<>();
  private final int width;
  private final int height;
  private int score = 0;
  private int x = 0;
  private int y = 0;
  public SnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    for(final int[] f : food) {
      this.food.add(new Cell(f[0], f[1]));
    }
  }

  public int move(String direction) {
    switch(direction) {
      case "D":
        this.y = this.y - 1;
        break;
      case "U":
        this.y = this.y + 1;
        break;
      case "R":
        this.x = this.x + 1;
        break;
      case "L":
        this.x = this.x - 1;
        break;
    }
    final Cell curr = new Cell(this.x, this.y);
    if (!this.snake.add(curr)) {
      return -1;
    }
    if(this.food.contains(curr)) {
      score++;
      this.food.remove(curr);
    }
    return score;
  }

  class Cell {
    private final int x;
    private final int y;

    Cell(final int x, final int y) {
      this.x = x;
      this.y = y;
    }

    public boolean equals(final Object obj) {
      if(obj == null) {
        return false;
      }
      if (!(obj instanceof Cell)) {
        return false;
      }
      if (this == obj) {
        return true;
      }
      final Cell cell = (Cell) obj;

      return this.x == cell.x && this.y == cell.y;
    }

    public int hashCode() {
      return Objects.hash(this.x, this.y);
    }

  }
}
