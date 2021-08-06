const infinityScrolling = {
    limit: 20,
    itemLength: 0,
    currentPage: 1,
    url: '',
    makeItemList: function () {},
    setScrollInfinity: function(target) {
        target.addEventListener('scroll', () => {
            const {
                scrollTop,
                scrollHeight,
                clientHeight
            } = document.documentElement;
            if (scrollTop + clientHeight >= scrollHeight - 5 && this.itemLength === this.limit) {
                this.itemLength = 0;
                this.getItemList(++this.currentPage);
            }
        }, { passive: true });
    },
    getItemList: function(page) {
        // this.showLoading(); 로딩 이미지를 보여주는 함수. 후에 추가해주자

        fetch(`${this.url}&page=${page}`)
            .then(res => res.json())
            .then(myJson => {
                console.log(myJson);
                this.itemLength = myJson.length;
                this.makeItemList(myJson);
            }).catch(err => {
            console.log(err);
        }).then(() => {
            // this.hideLoading(); 로딩 이미지를 숨기는 함수. 후에 추가해주자
        });
    }
}

//profilejs
const menu = document.querySelector('label .menu');
menu.addEventListener('click', () => {
    color();
});
const other_color = 'rgba(255, 255, 255, 0.421)';


function color() {
    const currentColor = menu.style.background;
    if (currentColor === '') {
        menu.style.background = other_color;
    } else {
        menu.style.background = '';
    }
}



const menuListUlElem = document.querySelector('.menu-list ul');
const chk = document.querySelector('#chk');
chk.addEventListener('change', () => {
    console.log(chk.checked);

    if (chk.checked) {
        // menuListUlElem.style.opacity = 1;
        menuListUlElem.style.display = 'block';
    } else {
        menuListUlElem.style.display = 'none';
    }
});

window.onload = function () {
    menuListUlElem.style.display = 'none';
}