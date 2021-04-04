import { render } from '../node_modules/lit-html/lit-html.js';
import page from '../node_modules/page/page.mjs';

import {logout} from './api/data.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';
import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { searchArticlesPage } from './views/search.js';

const main = document.getElementById('main-content');

page('/',decorateContext,homePage);
page('/login',decorateContext,loginPage);
page('/register',decorateContext, registerPage);
page('/details/:id',decorateContext,detailsPage);
page('/catalog',decorateContext,catalogPage);
page('/create',decorateContext,createPage);
page('/edit/:id',decorateContext,editPage);
page('/search', decorateContext, searchArticlesPage);


page.start();
document.getElementById('logoutBtn').addEventListener('click', async ()=>{
    await logout();
    page.redirect('/');
    setUserNav();
})
setUserNav();





function decorateContext(ctx, next) {
    ctx.render = (content) => render(content, main);
    ctx.setUserNav = setUserNav;
    next();
}

function setUserNav(){
    const userId = sessionStorage.getItem('userId');
    if (userId != null){
        document.getElementById('user').style.display = 'inline-block';
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('user').style.display = 'none';
        document.getElementById('guest').style.display = 'inline-block';
    }
}