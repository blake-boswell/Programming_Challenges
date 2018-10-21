from sys import stdin

# uVA 10315

def readHands():
    """ Return White and Black's hands from the input """
    hands = stdin.readline().split()
    if not hands:
        return None, None
    whiteHand = list()
    blackHand = list()
    for i in range(5):
        blackHand.append(hands[i])
    for i in range(5,10):
        whiteHand.append(hands[i])
    return blackHand, whiteHand

def compareHands(blackHand, whiteHand):
    # print("Black")
    blackScore, blackTieBreaker = getHandValue(blackHand)
    # print("White")
    whiteScore, whiteTieBreaker = getHandValue(whiteHand)
    # print(whiteScore)
    # print(blackScore)

    if blackScore > whiteScore:
        print("Black wins.")
    elif whiteScore > blackScore:
        print("White wins.")
    else:
        # Check tie breakers
        if blackScore == 1 or blackScore == 6:
            print(getHighCardWinnerMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 2:
            print(getPairWinnerMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 3:
            print(getTwoPairWinnerMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 4:
            print(getThreeOfKindMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 5 or blackScore == 9:
            print(getStraightMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 7:
            print(getFullHouseMessage(whiteTieBreaker, blackTieBreaker))
        elif blackScore == 8:
            print(getFourOfKindMessage(whiteTieBreaker, blackTieBreaker))


def getHighCardWinnerMessage(sortedWhiteHand, sortedBlackHand):
    # print("High Card")
    for i in range(0, len(sortedBlackHand)):
        if sortedBlackHand[i] > sortedWhiteHand[i]:
            return "Black wins."
        elif sortedBlackHand[i] < sortedWhiteHand[i]:
            return "White wins."
    return "Tie."

def getPairWinnerMessage(whiteSortedMatches, blackSortedMatches):

    whitePair = whiteSortedMatches[0]
    blackPair = blackSortedMatches[0]
    if whitePair > blackPair:
        return "White wins."
    elif whitePair < blackPair:
        return "Black wins."
    else:
        for i in range(1, len(blackSortedMatches)):
            if whiteSortedMatches[i] > blackSortedMatches[i]:
                return "White wins."
            elif whiteSortedMatches[i] < blackSortedMatches[i]:
                return "Black wins."
    return "Tie."

def getTwoPairWinnerMessage(whiteSortedMatches, blackSortedMatches):
    whiteHighPair = whiteSortedMatches[0]
    whiteLowPair = whiteSortedMatches[1]
    whiteRemainingCard = whiteSortedMatches[2]
    blackHighPair = blackSortedMatches[0]
    blackLowPair = blackSortedMatches[1]
    blackRemainingCard = blackSortedMatches[2]

    if whiteHighPair > blackHighPair:
        return "White wins."
    elif whiteHighPair < blackHighPair:
        return "Black wins."
    elif whiteLowPair > blackLowPair:
        return "White wins."
    elif whiteLowPair < blackLowPair:
        return "Black wins."
    elif whiteRemainingCard > blackRemainingCard:
        return "White wins."
    elif whiteRemainingCard < blackRemainingCard:
        return "Black wins."
    return "Tie."

def getThreeOfKindMessage(whiteSortedMatches, blackSortedMatches):
    if whiteSortedMatches[0] > blackSortedMatches[0]:
        return "White wins."
    elif whiteSortedMatches[0] < blackSortedMatches[0]:
        return "Black wins."
    else:
        return "Tie."

def getStraightMessage(whiteHighCard, blackHighCard):
    if whiteHighCard > blackHighCard:
        return "White wins."
    elif whiteHighCard < blackHighCard:
        return "Black wins."
    else:
        return "Tie."

def getFullHouseMessage(whiteThreeOfKindCard, blackThreeOfKindCard):
    if whiteThreeOfKindCard > blackThreeOfKindCard:
        return "White wins."
    elif whiteThreeOfKindCard < blackThreeOfKindCard:
        return "Black wins."
    else:
        return "Tie."

def getFourOfKindMessage(whiteSortedMatches, blackSortedMatches):
    if whiteSortedMatches[0] > blackSortedMatches[0]:
        return "White wins."
    elif whiteSortedMatches[0] < blackSortedMatches[0]:
        return "Black wins."
    else:
        return "Tie."

            
                    


def getHandValue(hand):
    """ Returns the type of hand, and the tie breaker value """
    values = {
        "HIGH_CARD": 1,
        "PAIR": 2,
        "TWO_PAIR": 3,
        "THREE_OF_KIND": 4,
        "STRAIGHT": 5,
        "FLUSH": 6,
        "FULL_HOUSE": 7,
        "FOUR_OF_KIND": 8,
        "STRAIGHT_FLUSH": 9
    }

    # Split hand into suites hand and value hand
    handSuites = [card[-1] for card in hand]
    handValues = list()
    for card in hand:
        card = card[:-1]
        if card == 'T':
            card = 10
        elif card == 'J':
            card = 11
        elif card == 'Q':
            card = 12
        elif card == 'K':
            card = 13
        elif card == 'A':
            card = 14
        handValues.append(int(card))
    # Sort high to low
    sortedHandValues = sorted(handValues, reverse=True)
    # print(handSuites)
    # print(handValues)
    highCard = sortedHandValues[0]

    isFlush = checkFlush(handSuites)
    isStraight = checkStraight(sortedHandValues)
    # Check straight-flush
    if isFlush and isStraight:
        return values["STRAIGHT_FLUSH"], highCard

    # Get the matches in the hand and sort them by most occuring card
    matches = getMatches(sortedHandValues)
    # Card:count dictionary
    # print("Matches: ", matches)
    sortedMatches = sorted(matches, key= matches.get, reverse=True)
    # print("Sorted Matches: ", sortedMatches)
    greatestMatchCount = matches[sortedMatches[0]]
    # print("Greatest match count: ", greatestMatchCount)

     # Check four of a kind
    if greatestMatchCount == 4:
        return values["FOUR_OF_KIND"], sortedMatches

    # Check flush
    if isFlush:
        return values["FLUSH"], sortedHandValues

    # Check Full_House
    isFullHouse, threeOfKindCard = checkIsFullHouse(matches)
    if isFullHouse:
        return values["FULL_HOUSE"], threeOfKindCard

    # Check straight
    if isStraight:
        return values["STRAIGHT"], highCard

    if greatestMatchCount == 3:
        return values["THREE_OF_KIND"], sortedMatches

    # Check 2 pair
    isTwoPair = checkTwoPair(matches)
    if isTwoPair:
        return values["TWO_PAIR"], sortedMatches

    # Check pair
    if greatestMatchCount == 2:
        return values["PAIR"], sortedMatches

    # Return high card
    return values["HIGH_CARD"], sortedHandValues

def getTieBreaker(matches, handValue):
    if handValue == 2:
        return False
        # Pair
    elif handValue == 3:
        return False
        # Two pair
    elif handValue == 4:
        return False
        # Three of kind
    elif handValue == 5:
        return False
        # Straight
    elif handValue == 6:
        return False
        # Flush
    elif handValue == 7:
        return False
        # Full house
    elif handValue == 8:
        # Four of kind
        for value in matches:
            print("count: ", matches[value])
            if matches[value] == 4:
                return value
    elif handValue == 9:
        return False
        # Straight flush

def checkFlush(handSuites):
    uniqueSuites = set(handSuites)
    if len(uniqueSuites) == 1:
        return True

def checkStraight(sortedHandValues):
    """ Hand is sorted High to low """
    for i in range(1,len(sortedHandValues)):
        if sortedHandValues[i] != (sortedHandValues[i-1] - 1):
            return False
    return True

def getMatches(sortedHandValues):
    """ Returns a dictionary { cardValue: count } in the same order as the handValues appear in the input """
    # For ea card in sortedHandValues list
    matches = dict()
    for card in sortedHandValues:
        if card in matches:
            matches[card] += 1
        else:
            matches[card] = 1
    return matches

# def sortByOccurance(matches):
#     sortedMatcheKeys = sorted(matches, key= matches.get, reverse=True)
    

def checkIsFullHouse(matches):
    threeOfKindCard = None
    pairCard = None
    for cardValue in matches:
        # print("CARD VAL: ", cardValue)
        # print(matches[cardValue])
        if matches[cardValue] == 3:
            threeOfKindCard = cardValue
            if pairCard:
                return True, threeOfKindCard
        elif matches[cardValue] == 2:
            pairCard = cardValue
            if threeOfKindCard:
                return True, threeOfKindCard
    return False, None

def checkTwoPair(sortedMatchValues):
    pairCount = 0
    for cardValue in sortedMatchValues:
        if sortedMatchValues[cardValue] == 2:
            pairCount += 2
        if pairCount == 4:
            return True
    return False


def main():
    """ Main entry point of the app """

    blackHand, whiteHand = readHands()
    while blackHand and whiteHand:
        compareHands(blackHand, whiteHand)
        blackHand, whiteHand = readHands()
    

if __name__ == "__main__":
    """ Executed when run from cmd line """
    main()