function displayTime() {
    const now = new Date();
    document.getElementById('timeDisplay').textContent = now.toLocaleTimeString();
    document.getElementById('timeDisplay').style.fontSize = "2rem";
    document.getElementById('timeDisplay').style.backgroundColor = "rgba(31, 37, 68, 0.7)";
    document.getElementById('timeDisplay').style.color = "#FFD0EC";
    document.getElementById('timeDisplay').style.padding = "10px";
    document.getElementById('timeDisplay').style.borderRadius = "10px";
}

// Cursor hover effects
const hoverParagraph = document.getElementById('hoverParagraph');
const cursorStatus = document.getElementById('cursorStatus');

hoverParagraph.onmouseenter = () => cursorStatus.textContent = 'Discover the brilliance of each piece';
hoverParagraph.onmouseleave = () => cursorStatus.textContent = 'Experience the elegance of our collection';

// Image hover effects
const hoverImage = document.getElementById('hoverImage');
const hoverText = document.getElementById('hoverText');

hoverImage.onmouseenter = () => {
    hoverImage.style.transform = 'translateY(10px)';
    hoverText.style.display = 'block';
};

hoverImage.onmouseleave = () => {
    hoverImage.style.transform = 'translateY(0)';
    hoverText.style.display = 'none';
};

// Background color change on click
document.body.onclick = () => {
    document.body.style.background = "linear-gradient(135deg, #1F2544, #A64AC9, #FFD0EC)";
};

// Back to Index
function goBackToIndex() {
    window.location.href = "../index.html";  // Adjust the path to your index page if necessary
}
