import { render } from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';


import {logout} from './api/data.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { detailsPage } from './views/details.js';
import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';



const main = document.querySelector('main');
setUserNav();

page('/',decorateContext,homePage);
page('/register',decorateContext,registerPage);
page('/login',decorateContext,loginPage);
page('/catalog', decorateContext, catalogPage);
page('/details/:id',decorateContext, detailsPage);
page('/create',decorateContext, createPage);

page.start();


document.getElementById('logout').addEventListener('click', async ()=>{
    await logout();
    page.redirect('/');
    setUserNav();
})

function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}

function setUserNav(){
    const email = sessionStorage.getItem('email');
    if (email != null){
        document.getElementById('catalog').style.display = '';
        document.getElementById('create').style.display = '';
        document.getElementById('logout').style.display = '';
        document.getElementById('register').style.display = 'none';
        document.getElementById('login').style.display = 'none';
    } else {
        document.getElementById('catalog').style.display = '';
        document.getElementById('create').style.display = 'none';
        document.getElementById('logout').style.display = 'none';
        document.getElementById('register').style.display = '';
        document.getElementById('login').style.display = '';
    }
}