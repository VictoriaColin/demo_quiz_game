package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionsDTO {
    @JsonProperty("clues")
    private List<Clues> clues;

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }

    public static class Clues {
        @JsonProperty("canon")
        private String canon;
        @JsonProperty("game")
        private Game game;
        @JsonProperty("category")
        private Category category;
        @JsonProperty("invalidCount")
        private String invalidCount;
        @JsonProperty("gameId")
        private String gameId;
        @JsonProperty("categoryId")
        private String categoryId;
        @JsonProperty("value")
        private String value;
        @JsonProperty("question")
        private String question;
        @JsonProperty("answer")
        private String answer;
        @JsonProperty("id")
        private String id;

        public String getCanon() {
            return canon;
        }

        public void setCanon(String canon) {
            this.canon = canon;
        }

        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getInvalidCount() {
            return invalidCount;
        }

        public void setInvalidCount(String invalidCount) {
            this.invalidCount = invalidCount;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class Game {
        @JsonProperty("canon")
        private String canon;
        @JsonProperty("aired")
        private String aired;

        public String getCanon() {
            return canon;
        }

        public void setCanon(String canon) {
            this.canon = canon;
        }

        public String getAired() {
            return aired;
        }

        public void setAired(String aired) {
            this.aired = aired;
        }
    }

    public static class Category {
        @JsonProperty("canon")
        private String canon;
        @JsonProperty("title")
        private String title;
        @JsonProperty("id")
        private String id;

        public String getCanon() {
            return canon;
        }

        public void setCanon(String canon) {
            this.canon = canon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
