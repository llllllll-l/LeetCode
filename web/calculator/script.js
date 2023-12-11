let runningTotal = 0;
let buffer = "0";
let prevOpp;

const screen = document.querySelector('.screen');

function btnClick(value) {
    if (isNaN(value)) { /** is number = isNaN */
        handleSymbol(value);
    } else {
        handleNum(value);
    }
    screen.innerText = buffer;
}

function handleSymbol(symbol) {
    switch(symbol) {
        case 'C':
            buffer = '0';
            runningTotal = 0;
            break;
        case '=':
            if (prevOpp === null) {
                return
            }
            flushOpp(parseInt(buffer));
            prevOpp = null;
            buffer = runningTotal;
            runningTotal = 0;
            break;
        case '←':
            if (buffer.length === 1) {
                buffer = '0';
            } else {
                buffer = buffer.substring(0, buffer.length -1);
            }
            break;
        case '+':
        case '-':
        case '×':
        case '÷':
            handleMath(symbol);
            break;
    }
}



function handleMath(symbol) {
    if (buffer === '0') {
        return
    }

    const intBuffer = parseInt(buffer);

    if (runningTotal === 0) {
        runningTotal = intBuffer;
    } else {
        flushOpp(intBuffer);
    }

    prevOpp = symbol;
    buffer = '0';
}

function flushOpp(intBuffer) {
    if (prevOpp === '+') {
        runningTotal += intBuffer;
    } else if (prevOpp === '-') {
        runningTotal -= intBuffer;
    } else if (prevOpp === '×') {
        runningTotal *= intBuffer;
    } else if (prevOpp === '÷') {
        if (intBuffer != 0) {
            runningTotal /= intBuffer;
        }
    }
}

function handleNum(numberString) {
    if (buffer === '0') {
        buffer = numberString;
    } else {
        buffer += numberString;
    }
}

function init() {
    document.querySelector('.calc-btns').addEventListener('click', (event) => {
        btnClick(event.target.innerText);
    })
}

init();
