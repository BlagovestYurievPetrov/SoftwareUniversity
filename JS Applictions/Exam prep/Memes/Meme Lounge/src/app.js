import { render } from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';

import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { createPage } from './views/create.js';
import { catalogPage } from './views/catalog.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';
import { profilePage } from './views/profile.js';


import {logout} from './api/data.js';

const main = document.querySelector('main');
setUserNav();

page('/', decorateContext, homePage);
page('/login', decorateContext, loginPage);
page('/register', decorateContext, registerPage);
page('/create', decorateContext,createPage);
page('/catalog',decorateContext,catalogPage);
page('/details/:id',decorateContext,detailsPage);
page('/edit/:id',decorateContext,editPage);
page('/profile',decorateContext,profilePage)

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
        document.querySelector('div.profile > span').textContent = `Welcome, ${email}`;
        document.querySelector('.user').style.display = '';
        document.querySelector('.guest').style.display = 'none';
    } else {
        document.querySelector('.guest').style.display = '';
        document.querySelector('.user').style.display = 'none';
    }
}
