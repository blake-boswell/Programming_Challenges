from sys import stdin

def hasRepetitiveLetters(word):
    uniqueLetters = set(word)
    if len(uniqueLetters) != len(word):
        return True
    return False

def main():
    """ Main entry point of the app """
    wordCount = input()
    print("Word count: ",wordCount)
    dictionary = list()
    for i in range(0, int(wordCount)):
        
        word = input()
        print(word)
        dictionary.append(word)

    # separate dictionary into lists of similar length
    wordsByLength = dict()
    wordsWithRepition = list()
    for word in dictionary:
        # Separate into different sized key:value pairs (length: word)
        existingWords = wordsByLength.get(len(word))
        if existingWords == None:
            words = list()
            words.append(word)
            wordsByLength[len(word)] = words
        else:
            existingWords.append(word)
            updatedWords = { len(word): existingWords }
            wordsByLength.update(updatedWords)
        # Separate into different dictionaries based on repetitive letters
        if hasRepetitiveLetters(word):
            wordsWithRepition.append(word)
            

    print("WORDS_BY_LENGTH: ", wordsByLength)
    print("WORDS_WITH_REPITITON: ", wordsWithRepition)

    # Read input lines
    inputLines = list()
    while True:
        line = input()
        if line == '':
            print("BREAKING")
            break
        inputLines.append(line)
        keys = dict()
        # Get the unique words from the line
        words = set(line.split(' '))
        print("\n\nUnique words: ", words, "\n")
        possibleTranslation = True
        for word in words:
            wordLength = len(word)
            numOfWordsWithSameLength = len(wordsByLength[wordLength])
            if numOfWordsWithSameLength == 1:
                dictionaryWord = wordsByLength[wordLength][0]
                # Check to see if its a possible match due to repetition
                # Ex: aaa cannot be mapped to and
                if len(set(dictionaryWord)) == len(set(word)):
                    for i in range(0, wordLength):
                        if word[i] not in keys:
                            # Add to key
                            print("Adding ", word[i], " to be key for ", dictionaryWord[i])
                            keys[word[i]] = dictionaryWord[i]
                        elif keys[word[i]] != dictionaryWord[i]:
                            # Multiple mappings to this encrpyted letter -> not possible translation
                            possibleTranslation = False
                            break
                    if possibleTranslation = False:
                        break
                else:
                    # No possible translation
                    possibleTranslation = False
                    break

            elif len(wordsWithRepition) > 0:
                print("\n\n[REP CHECK -- BEGIN] Starting rep check on: ", word)
                # List to hold possible matches for this word
                possibleMatches = list()
                repetitionExists = False
                for i in range(1, wordLength):
                    # find indexes of repetition
                    if word[i-1] == word[i]:
                        print("[REP CHECK] Found repetition in ", word, " with letters ", word[i-1], " and ", word[i])
                        # Found repetition
                        if repetitionExists:
                            # Check existing possible matches
                            for wordWithRep in possibleMatches:
                                # Remove the ones that no longer match the pattern
                                if wordWithRep[i-1] != wordWithRep[i]:
                                    possibleMatches.remove(wordWithRep)
                                    print("[REP CHECK -- RM] Removing ", wordWithRep, " from our possible matches list: ", possibleMatches)
                        else:
                            # Look in our repetition array for words with this same pattern
                            for wordWithRep in wordsWithRepition:
                                if wordWithRep[i-1] == wordWithRep[i]:
                                    # add to list
                                    possibleMatches.append(wordWithRep)
                                    print("[REP CHECK] Adding ", wordWithRep, " to our possible matches list: ", possibleMatches)
                                    repetitionExists = True
                            if len(possibleMatches) == 0:
                                # Repetition won't help with this word
                                print("No rep found")
                                break
                print("[REP CHECK -- END] Results of rep check: ", possibleMatches)
                if len(possibleMatches) == 1:
                    for i in range(0, wordLength):
                        # Add letters to key
                        print("[REP CHECK -- ADD TO KEY] Adding ", word, "'s letters to key mapping to ", possibleMatches[0])
                        keys[word[i]] = possibleMatches[0][i]
            # Search dictionary for words where letters match in place
            # Ex: *ane -> jane
            # If multiple occurances, save occurances to a list to check after the loop ends (maybe dictionary encrypted: list of occurances)
            # If single occurance, add new letter to the key, remove the encrypted word from the list of unknowns, and reset the loop 
            # This will decrease the number of loops, because our key will now be updated which may help us solve others
            print("For ", word, " we have ", numOfWordsWithSameLength, " options")
        print("Our key so far: ", keys)
        print("Is Translation possible?: ", possibleTranslation)
        

    print("DICT:", dictionary)
    print("LINES:", inputLines)


if __name__ == "__main__":
    """ Executed when run from cmd line """
    main()