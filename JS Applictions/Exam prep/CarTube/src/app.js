import { render } from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';

import {homePage} from '../src/views/home.js'

import {logout} from './api/data.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';
import { loginPage } from './views/login.js';
import { profilePage } from './views/profile.js';
import { registerPage } from './views/register.js';

const main = document.querySelector('main');
setUserNav();

page('/',decorateContext,homePage);
page('/login',decorateContext,loginPage);
page('/register',decorateContext,registerPage);
page('/catalog',decorateContext,catalogPage);
page('/details/:id',decorateContext,detailsPage);
page('/edit/:id',decorateContext,editPage);
page('/create',decorateContext,createPage);
page('/profile',decorateContext,profilePage);

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
    const username = sessionStorage.getItem('username');
    if (username != null){
        document.getElementById('welcome').textContent = `Welcome ${username}`;
        document.getElementById('profile').style.display = '';
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('guest').style.display = '';
        document.getElementById('profile').style.display = 'none';
    }
}