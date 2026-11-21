# Sicurezza

Questo documento descrive come segnalare un problema di sicurezza in AgentCraft e quali
confini il progetto si impegna a rispettare.

## Segnalazioni

Non aprire una issue pubblica per una vulnerabilità. Usa il canale privato
`Security > Report a vulnerability` su GitHub oppure scrivi all'indirizzo indicato
nel profilo del manutentore. Inserisci:

- componente coinvolto (`mod/`, `foreman/` oppure `assets-src/`);
- versione e piattaforma;
- passi per riprodurre il problema;
- impatto atteso ed effettivo.

Non allegare token, credenziali, sessioni o dati di utenti. Se la segnalazione contiene
un segreto, segnala solo il percorso e la natura del dato: la rotazione va decisa dal
manutentore.

## Confini del progetto

- Il mod non esegue mai push e non unisce modifiche senza una decisione esplicita
  dell'utente all'interno del gioco.
- Ogni agente lavora in un worktree dedicato sotto `agentcraft/<agente>/<task>`: il
  checkout dell'utente e i suoi branch non vengono toccati.
- I messaggi del protocollo vengono validati prima dell'uso. Campi sconosciuti sono
  ignorati e gli enum non riconosciuti diventano `UNKNOWN`.
- Gli snapshot del protocollo vengono controllati per integrità; un checkpoint
  corrotto viene segnalato e non accettato come valido.
- Nessun segreto va nei file del protocollo, negli snapshot, nei log o negli asset
  generati.
- I comandi lanciati dagli agenti restano confinati al worktree assegnato e non
  ricevono privilegi elevate automatiche.

## Aggiornamenti

Le dipendenze del coordinatore vanno aggiornate con il lockfile committato e la
verifica dei test. Non usare-versioni senza hash quando il canale di distribuzione
le supporta. Ogni modifica che tocca il protocollo richiede l'aggiornamento degli
snapshot e la verifica dei test del mod.
