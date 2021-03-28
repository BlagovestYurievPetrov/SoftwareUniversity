import { render } from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';

import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { catalogPage } from './views/catalog.js';
import { addPage } from './views/add.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';


import {logout} from './api/data.js';
const main = document.querySelector('main');
setUserNav();

page('/',decorateContext,catalogPage)
page('/register',decorateContext, registerPage);
page('/login',decorateContext,loginPage);
page('/add',decorateContext, addPage);
page('/details/:id',decorateContext,detailsPage);
page('/edit/:id',decorateContext,editPage);

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
        document.getElementById('welcome').style.display = '';
        document.getElementById('welcome').textContent = `Welcome, ${email}`;
        document.getElementById('logout').style.display = '';
        document.getElementById('login').style.display = 'none';
        document.getElementById('register').style.display = 'none';
    } else {
        document.getElementById('login').style.display = '';
        document.getElementById('register').style.display = '';
        document.getElementById('welcome').style.display = 'none';
        document.getElementById('logout').style.display = 'none';
    }
}