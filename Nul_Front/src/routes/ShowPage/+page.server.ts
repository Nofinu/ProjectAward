import { BACKEND_URL } from '$env/static/private';
import { fail, redirect } from '@sveltejs/kit';
import type { Actions } from './$types';

export const actions: Actions = {
  vote: async ({ request, cookies, fetch }) => {
    const token = cookies.get('twitch_token');
    if (!token) redirect(303, '/');

    const formData = await request.formData();
    const game = Number(formData.get('game'));
    const voteMonth = Number(formData.get('month'));

    if (!Number.isInteger(game) || !Number.isInteger(voteMonth)) {
      return fail(400, { erreur: 'Données invalides' });
    }

    const res = await fetch(`${BACKEND_URL}/api/vote`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify({ game, voteMonth })
    });

    if (!res.ok) {
      return fail(res.status, { erreur: 'Le vote a échoué', game });
    }

    return { succes: true, game };
  }
};