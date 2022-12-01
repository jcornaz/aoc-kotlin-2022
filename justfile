set dotenv-load

@_list:
	just --list --unsorted

year := "2022"
day := "1"

# Fetch your personal input (requires an `AOC_SESSION` environment variable)
get-input:
	curl -s \
		-H "Cookie: session=$AOC_SESSION" \
		"https://adventofcode.com/{{year}}/day/{{day}}/input" \
		> "src/test/resources/day$(printf "%02d" {{day}})_input.txt"

# Perform all verifications (compile, test, lint, etc.)
verify:
    ./gradlew check

# Watch the source files and run the test of the current day when source changes
watch:
    ./gradlew test --tests Day$(printf "%02d" {{day}})Test --continuous

# Run the tests of the current day
test-day:
	./gradlew test --tests Day$(printf "%02d" {{day}})Test

# Clean up compilation output
clean:
    ./gradlew clean

# Install a git hook to run tests before every commits
install-git-hooks:
	echo '#!/usr/bin/env sh' > .git/hooks/pre-commit
	echo 'just verify' >> .git/hooks/pre-commit
	chmod +x .git/hooks/pre-commit
