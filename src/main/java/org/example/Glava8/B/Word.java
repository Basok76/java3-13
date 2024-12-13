package org.example.Glava8.B;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private List<Symbol> symbols;

    public Word(String word) {
        symbols = new ArrayList<>();
        for (char c : word.toCharArray()) {
            symbols.add(new Symbol(c));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Symbol symbol : symbols) {
            result.append(symbol.getValue());
        }
        return result.toString();
    }
}