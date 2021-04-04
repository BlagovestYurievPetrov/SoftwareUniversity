import { html } from '../../node_modules/lit-html/lit-html.js';
import { getRecentArticles } from '../api/data.js';

const articles = await getRecentArticles();
const jsArticles = articles.filter((x)=>x.category==='JavaScript');
const cArticles = articles.filter((x)=>x.category==='C#');
const javaArticles = articles.filter((x)=>x.category==='Java');
const pythonArticles = articles.filter((x)=>x.category==='Python');
console.log(articles);


const homeTemplate = (jsArticles,cArticles,javaArticles,pythonArticles) => html`
<section id="home-page" class="content">
    <h1>Recent Articles</h1>
    <section class="recent js">
        <h2>JavaScript</h2>
       ${jsArticles.length===0?html`<h3 class="no-articles">No articles yet</h3>`:html`
       ${articleTemplate(jsArticles[0])}
       `}
    </section>
    <section class="recent csharp">
        <h2>C#</h2>
        ${cArticles.length===0?html`<h3 class="no-articles">No articles yet</h3>`:html`
       ${articleTemplate(cArticles[0])}
       `}
    </section>
    <section class="recent java">
        <h2>Java</h2>
        ${javaArticles.length===0?html`<h3 class="no-articles">No articles yet</h3>`:html`
       ${articleTemplate(javaArticles[0])}
       `}
        </article>
    </section>
    <section class="recent python">
        <h2>Python</h2>
        ${pythonArticles.length===0?html`<h3 class="no-articles">No articles yet</h3>`:html`
       ${articleTemplate(pythonArticles[0])}
       `}
    </section>
</section>
`

const articleTemplate = (article) => html`
<article>
            <h3>${article.title}</h3>
            <p>${article.content}</p>
            <a href="/details/${article._id}" class="btn details-btn">Details</a>
`

export async function homePage(ctx) {
    ctx.render(homeTemplate(jsArticles,cArticles,javaArticles,pythonArticles));
}

