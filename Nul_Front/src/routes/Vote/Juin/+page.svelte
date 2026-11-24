<script lang="ts">
    import '../../../Css/VotePage.css'
    import { enhance } from '$app/forms';
    import { Games } from '../../../Data/Juin_Data.js';

    let { form } = $props();

    const month = 7;


    let popupVisible = $state(false);

    $effect(() => {
        if (form?.erreur || form?.succes) {
            popupVisible = true;
            const timer = setTimeout(() => (popupVisible = false), 4000);
            return () => clearTimeout(timer); // évite les timers qui s'empilent
        }
    });

    function fermer() {
        popupVisible = false;
    }
</script>

{#if popupVisible && (!form?.succes || form?.succes)}
    <div
        class="overlay"
        role="presentation"
        onclick={(e) => e.target === e.currentTarget && fermer()}
    >
        <div
            class="popup"
            class:popup-erreur={form?.erreur}
            class:popup-succes={form?.succes}
            role="alertdialog"
            aria-live="assertive"
        >
            <p>
                {#if form?.erreur}
                    Une erreure est survenue
                {:else}
                    Vote enregistré pour le jeu {form?.game} !
                {/if}
            </p>
            <button type="button" class="popup-close" onclick={fermer}>OK</button>
        </div>
    </div>
{/if}

<div class="grid">
    {#each Games as game}
        <div class="game">
            <h2 class="gameName">{game.name}</h2>
            <img src={game.image} alt="game screne capture">
            <p>{game.description}</p>
            <div class="steamContainer">
            <div class="tagsContainer">
                {#each game.tags as tag}
                <div class="tags">{tag}</div>
                {/each}
                
            </div>
            <a class="steamPage" href={game.steamPage} target="_blank">Page steam</a>
            </div>
        
        
            <form method="POST" action="?/vote" use:enhance>
                <input type="hidden" name="game" value={game.id} />
                <input type="hidden" name="month" value={month} />
                <button>Voter</button>
            </form>
        </div>
    {/each}
</div>
