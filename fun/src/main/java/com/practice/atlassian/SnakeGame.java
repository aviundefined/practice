package com.practice.atlassian;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SnakeGame {
  private final int width;
  private final int height;
  private final int[][] food;
  private final Set<Cell> occupied = new HashSet<>();
  private final Deque<Cell> snake = new ArrayDeque<>();
  private int foodIndex = 0;

  public SnakeGame(final int width, final int height, final int[][] food){
    this.width = width;
    this.height = height;
    this.food = food;
    this.snake.addFirst(new Cell(0, 0));
    this.occupied.add(new Cell(0, 0));
  }

  public int move(String direction) {
    final Cell currHead = snake.getFirst();
    int x = currHead.x;
    int y = currHead.y;
    switch (direction) {
      case "D" -> x++;
      case "U" -> x--;
      case "L" -> y--;
      case "R" -> y++;
    }
    if (wallHit(x, y)) {
      return -1;
    }
    final Cell newHead = new Cell(x, y);
    snake.addFirst(newHead);
    if (this.foodIndex < this.food.length) {
      final int[] currFood = food[foodIndex];
      if(currFood[0] == newHead.x && currFood[1] == newHead.y) {
        foodIndex++;
      } else{
        final Cell tail = snake.removeLast();
        occupied.remove(tail);
      }
    } else {
      final Cell tail = snake.removeLast();
      occupied.remove(tail);
    }

    if(occupied.contains(newHead)) {
      return -1;
    }
    occupied.add(newHead);
    return snake.size() - 1;
  }

  private boolean wallHit(int x, int y) {
    return x < 0 || y < 0 || x >= this.height || y >= this.width;
  }

  private static class Cell {
    private final int x;
    private final int y;

    private Cell(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cell cell = (Cell) o;
      return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
