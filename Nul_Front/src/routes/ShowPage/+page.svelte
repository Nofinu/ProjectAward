<script lang="ts">
    import { enhance } from '$app/forms';

    let { form } = $props();

    const month = 1;
    const games = [1, 2, 3, 4, 5, 6];
</script>

<h1>Page where to show Games</h1>

{#if form?.erreur}
    <p class="erreur">{form.erreur}</p>
{:else if form?.succes}
    <p class="succes">Vote enregistré pour le jeu {form.game} !</p>
{/if}

<div class="grid">
    {#each games as id}
        <div class="game">
            <h2>Game {id}</h2>
            <p>Description</p>

            <form method="POST" action="?/vote" use:enhance>
                <input type="hidden" name="game" value={id} />
                <input type="hidden" name="month" value={month} />
                <button>Vote game</button>
            </form>
        </div>
    {/each}
</div>

<style>
    .grid {
        width: auto;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
    }
    .game {
        width: 300px;
    }
    .erreur { color: crimson; }
    .succes { color: seagreen; }
</style>