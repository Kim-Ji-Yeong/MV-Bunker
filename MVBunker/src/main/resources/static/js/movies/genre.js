var slideIndex = 0; //slide index
var slideIndex2 = 0;
var slideIndex3 = 0;
var slideIndex4 = 0;

const eachGenreList = document.querySelectorAll('.each-genre');

eachGenreList.forEach((articleElem) => {
    const dataset = articleElem.dataset;
    const prevBtnElem = articleElem.querySelector('.prev');
    const nextBtnElem = articleElem.querySelector('.next');
    prevBtnElem.addEventListener('click', () => {
        dataset.slideIndex = String(Number(dataset.slideIndex) + 1);
        showSlides(1, articleElem);
    })
    nextBtnElem.addEventListener('click', () => {
        dataset.slideIndex = String(Number(dataset.slideIndex) + 1);
        showSlides(1, articleElem);
    })
    showSlides(0, articleElem);
})

// HTML 로드가 끝난 후 동작
window.onload=function(){

}


// Next/previous controls
// function moveSlides(n, articleElement) {
//     slideIndex = slideIndex + n
//     showSlides(slideIndex, articleElement);
// }

function showSlides(n, articleElement) {
    const dataset = articleElement.dataset;
    const slides = articleElement.getElementsByClassName("mySlides");
    // const size = slides.length;
    //
    // if ((n+1) > size) {
    //     n = 0;
    //     dataset.slideIndex = String(n);
    // }else if (n < 0) {
    //     n = (size-1);
    //     dataset.slideIndex = String(n);
    // }

    for (let i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    let result = Number(dataset.slideIndex)%2;
    slides[result].style.display = "block";
}

function show(){
    const img = document.querySelector(".x");
    img.setStyle()
}

const darknessElems = document.querySelectorAll('.darkness');
darknessElems.forEach(darknessElem => {
    darknessElem.addEventListener('mouseover', (e) => {
            const imgElem = e.currentTarget.parentNode.querySelector('.x');
            e.currentTarget.style.transform = 'scale(1.2)'
            imgElem.style.transform= 'scale(1.2)';
        })
    darknessElem.addEventListener('mouseout',(e) => {
        const imgElem = e.currentTarget.parentNode.querySelector('.x');
        e.currentTarget.style.transform = 'scale(1)'
        imgElem.style.transform = 'scale(1)';
    })
})
