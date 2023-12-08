function scrollToElement(elementSelector, instance = 0) {
    console.log(document.querySelectorAll(elementSelector))
    const elements = document.querySelectorAll(elementSelector);
    if (elements.length > instance) {
        console.log(elements[instance]);
        elements[instance].scrollIntoView({ behavior: "smooth" });
    }
}

// Get references to the links
const link1 = document.getElementById("link1");
const link2 = document.getElementById("link2");
const link3 = document.getElementById("link3");

// Add click event listeners to the links
link1.addEventListener('click', (event) => {
    event.preventDefault(); // <-- prevents default behavior of the anchor (<a>) will no longer jump back up to the top
    scrollToElement('.header');
});

link2.addEventListener('click', (event) => {
    event.preventDefault();
    scrollToElement('.header', 1);
});

link3.addEventListener('click', (event) => {
    event.preventDefault();
    scrollToElement('.column');
});
