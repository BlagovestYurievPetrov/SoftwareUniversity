function createCard (face, suit) {
    const validFace = ['2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K', 'A'];
    const validSuit = {
        'S': '\u2660',
        'H': '\u2665',
        'D': '\u2666',
        'C': '\u2663'
    }
    if (!validFace.includes(face)){
        throw new Error('Error');
    }
    if (!Object.keys(validSuit).includes(suit)){
        throw new Error('Error');
    }
    return {
        face,
        suit,
        toString() {
            return `${face}${validSuit[suit]}`;
        }


    }


}