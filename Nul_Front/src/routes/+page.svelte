<script>
  import "../Css/CountDown.css";
  // Svelte 5 (runes). Compte à rebours vers une date choisie.
  // Le calcul est refait à chaque chargement de la page.
  let target = $state("2026-12-05T12:00");
  let label = $state("Fin Des votes");
  let now = $state(Date.now());

  // Tick chaque seconde
  $effect(() => {
    const id = setInterval(() => (now = Date.now()), 1000);
    return () => clearInterval(id);
  });

  let targetMs = $derived(new Date(target).getTime());
  let valid = $derived(!Number.isNaN(targetMs));
  let diff = $derived(valid ? targetMs - now : 0);

  let parts = $derived.by(() => {
    const total = Math.max(0, Math.floor(diff / 1000));
    return [
      { value: Math.floor(total / 86400), unit: "jour" },
      { value: Math.floor((total % 86400) / 3600), unit: "heure" },
      { value: Math.floor((total % 3600) / 60), unit: "minute" },
      { value: total % 60, unit: "seconde" },
    ];
  });
</script>

<section class="WebsiteDescription">
  <h1>NUL Awards</h1>
  <div class="description">
    <p>
      Bienvenue au <b>NUL 2026</b> (Nofi Ultimate List)!
    </p>
    <p>
      Cet événement a pour but d’élire le meilleur petit jeu de l’année. Pour
      chaque mois, une liste d’une dizaine de jeux sortis durant un ce même mois
      de l’année (de décembre 2025 à novembre 2026) est disponible. Vous pouvez
      donc voter pour votre jeu préféré dans cette liste — ou tout simplement
      celui qui vous donne le plus envie.
    </p>

    <p>
      Les listes ont été faites par moi, donc si votre jeu préféré n’y figure
      pas, j’en suis désolé <span class="aside">(mais en vrai m'enfou pas lu)</span>

    <p>
      Une fois les 12 jeux de chaque mois élus, nous aurons un vote final pour
      déterminer le <b>GOTY</b> de cette année <span class="aside">(et non, ça ne seras pas GTA6 ici)</span>
    </p>
  </div>
</section>

<section class="countdown">
  <h1>{label || "Compte à rebours"}</h1>
  <div class="digits" role="timer" aria-live="off">
    {#each parts as { value, unit }}
      <div class="cell">
        <span class="num">{String(value).padStart(2, "0")}</span>
        <span class="unit">{unit}{value > 1 ? "s" : ""}</span>
      </div>
    {/each}
  </div>
</section>

<style>

  .aside {
    color: transparent;
  }

  h1 {
    margin: auto;
    margin-top: 25px;
    width: fit-content;
  }
  .description {
    margin: auto;
    text-align: left;
    width: 80%;
    font-size: 25px;
  }

  #ps {
    font-size: 15px;
  }
</style>
