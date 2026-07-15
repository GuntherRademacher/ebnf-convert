# Grammars & EBNF Conversions (Lecture 2)

This repository is where I’ve gathered, cleaned up, and converted the core syntax grammars discussed in the previous lecture. 

I took the classic Backus-Naur Form (BNF) rules shown in class and ran them through the `ebnf-convert` engine to generate modern, clean, W3C-compliant Extended Backus-Naur Form (EBNF). 

---

## Combined Grammars Index

| # | Grammar Name | Purpose / Pedagogical Concept | Input File | Output File |
|---|---|---|---|---|
| **1** | **Identifier List** | Right-recursion and list structures | `bnf_list_grammar.txt` | `ebnf_list_grammar.ebnf` |
| **2** | **Unambiguous Expressions** | Operator precedence & associativity | `bnf_expr_grammar.txt` | `ebnf_expr_grammar.ebnf` |
| **3** | **Unambiguous "Dangling Else"** | Resolving conditional matching ambiguity | `bnf_dangling_else.txt` | `ebnf_dangling_else.ebnf` |
| **4** | **Ambiguous Expressions** | Demonstrating structural grammar flaws | `bnf_ambiguous_expr.txt` | `ebnf_ambiguous_expr.ebnf` |
| **5** | **Toy Program & Assignment** | Step-by-step derivations & parse trees | `bnf_toy_program.txt` | `ebnf_toy_program.ebnf` |

---

## The 5 Grammars & Their Transformations

### 1. The Identifier List Grammar
* **Concept:** Demonstrates how context-free grammars construct list structures of arbitrary lengths using right-recursion.
* **The Shift to EBNF:** In BNF, the rule has to call itself recursively to handle commas. The converted EBNF cleans this up by making the comma and the trailing list optional using `?` (or group repetitions), making it much easier to read.
  
```text
/* Original BNF Source */
ident_list ::= identifier | identifier "," ident_list
identifier ::= [a-zA-Z_][a-zA-Z0-9_]*

/* Converted W3C EBNF */
ident_list ::= identifier ( "," ident_list )?
identifier ::= [a-zA-Z_] [a-zA-Z0-9_]*