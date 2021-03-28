import { html } from '../../node_modules/lit-html/lit-html.js';
import { getItemById, deleteRecord } from '../api/data.js';

const detailsTemplate = (item, isOwner , onDelete) => html`
<section id="movie-example">
    <div class="container">
        <div class="row bg-light text-dark">
            <h1>Movie title: ${item.title}</h1>

            <div class="col-md-8">
                <img class="img-thumbnail" src=${item.img}
                    alt="Movie">
            </div>
            <div class="col-md-4 text-center">
                <h3 class="my-3 ">Movie Description</h3>
                <p>${item.description}</p>
                ${isOwner?html`
                <a @click=${onDelete} class="btn btn-danger">Delete</a>
                <a class="btn btn-warning" href=${`/edit/${item._id}`}>Edit</a>`:html`
                <a class="btn btn-primary" href="#">Like</a>
                <span class="enrolled-span">Liked 1</span>`}
                
                
            </div>
        </div>
    </div>
</section>
`


export async function detailsPage(ctx){
    const id = ctx.params.id;
    const item = await getItemById(id);
    const userId = sessionStorage.getItem('userId');

    ctx.render(detailsTemplate(item,item._ownerId===userId,onDelete));

    async function onDelete(){
        const confirmed = confirm('Are you sure you want to delete this item?');
        if (confirmed){
            deleteRecord(item._id);
            ctx.page.redirect('/');
        }
    }
}