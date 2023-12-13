
const sidebarLinks = document.querySelectorAll('.sidebar a');
const contentSections = document.querySelectorAll('main > div')

// Add click event listeners to each anchor element and its children
sidebarLinks.forEach(link => {
  link.addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the default action (if needed)

    // Remove 'active' class from all links in the sidebar
    sidebarLinks.forEach(link => {
      link.classList.remove('active');
    });

    // Add 'active' class to the clicked <a> element
    this.classList.add('active');

    const targetId = this.getAttribute('data-target');

    // Remove 'active' class from all <div> in main section
    contentSections.forEach(section => {
        section.classList.remove('active');
    });

    // Add 'active' class to the <div> in main with the matching targetId
    const targetSelection = document.getElementById(targetId);
    if (targetSelection) {
        targetSelection.classList.add('active')
    }
  });

  // Find and add click event listeners to the span and h3 elements inside <a>
  const spanElement = link.querySelector('.material-symbols-outlined');
  const h3Element = link.querySelector('h3');

  spanElement.addEventListener('click', function(event) {
    link.click(); // Trigger the click event on the parent <a> element
  });

  h3Element.addEventListener('click', function(event) {
    link.click(); // Trigger the click event on the parent <a> element
  });


});

