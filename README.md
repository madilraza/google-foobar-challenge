# Google Foobar Challenges

My answers to [Google Foobar](https://www.google.com/foobar/) challenges, in Java.

Google Foo Bar is Google's secret recruiting process embedded within their search engine. There are 5 levels, each with a different number of challenges that follow a story.

After completing 3 levels, I was offered to send my solutions to a Google recruiter, that contacted me shortly after for a phone interview.

Each level included programming challenges of escalating difficultly. Level 4 was my limit.

## Index

### Level 1

1. [Origins and order](./level-1/challenge-1/Answer.java)

### Level 2

1. [Access codes](./level-2/challenge-1/Answer.java)
2. [Peculiar balance](./level-2/challenge-2/Answer.java)

### Level 3

1. [Save Beta Rabbit](./level-3/challenge-1/Answer.java)
2. [Spy snippets](./level-3/challenge-2/Answer.java)
3. [Hash it out](./level-3/challenge-3/Answer.java)

### Level 4

1. [Undercover underground](./level-4/challenge-1/Answer.java)

## Observations

### Level 1 - Challenge 1 "Origins and order"

Simple comparisons and string manipulations, where assumptions about the input data led to a more optimized code.

### Level 2 - Challenge 1 "Access codes"

Knowledge about Hash Maps was of great help here.

### Level 2 - Challenge 2 "Peculiar balance"

I had to research a bit to find an solution to this one, as it uses balanced ternary operations.

### Level 3 - Challenge 1 "Save Beta Rabbit"

Due to the scale of the tabular input, brute forcing the solution wasn't an option. Each cell had to use pre calculated values of previous cells.

### Level 3 - Challenge 2 "Spy snippets"

The challenge basically asked to do a crude implementation of an search engine. I did my best to find an optimized solution.

### Level 3 - Challenge 3 "Hash it out"

Normally brute forcing the solution does not work, but it did in this one. A colleague of mine actually did find a way to reverse the hash algorithm and make it O(n) complexity, I credit him in the code comments.

### Level 4 - Challenge 1 "Undercover underground"

The "Undercover underground" required a lot of math. I was able to find something related to the problem in a math forum, but the solution was in Python, and my submissions where in Java.

I tried to understand and convert the Python code, but the challenge timed out before I could finish. I was locked out of Google Foobar after this.
