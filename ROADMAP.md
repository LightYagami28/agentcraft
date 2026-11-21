# Roadmap

## Stato attuale

AgentCraft 1.0.0 (2026-10-05): mod Fabric per Minecraft 26.3 (Java 25, Loom 1.18, Gradle 9.7) che
rappresenta un team di agenti Claude in uno studio HQ percorribile. Il coordinatore Node/TypeScript
(`foreman/`) parla con il mod tramite il protocollo v1 (`docs/protocol.md`); gli asset sono generati
da `assets-src/`. Il mod ha ora anche test JUnit 5 (`mod/src/test`) per il controllo di salute degli
snapshot del protocollo.

Fonti verificate: `README.md`, `CHANGELOG.md`, `docs/SPEC.md`, `mod/build.gradle`, `foreman/test`.

## Obiettivi

- Completare la `Definition of done for v1` in `docs/SPEC.md`: flusso goal → pianificazione → worktree
  → decisione in mondo → revisione del diff → merge solo su azione esplicita dell'utente, mai push.
- Mantenere il rispetto dei vincoli di sicurezza del protocollo: worktree `agentcraft/<agente>/<task>`,
  nessuna modifica al branch dell'utente, nessun merge senza decisione esplicita.
- Far funzionare lo stesso flusso con backend `sim` e `claude`, con ripristino dello stato dopo il
  riavvio e disconnessione gestita con banchetto "disconnected".
- Raggiungere la barra visiva in `docs/visual-bar.md` e far passare la suite di screenshot QA
  (`tools/scenes/*.json`).
- Tenere il protocollo tollerante alle versioni future: campi sconosciuti ignorati, enum sconosciuti
  mappati su `UNKNOWN`.

## Prossimi passi

1. Completare e verificare il flusso end-to-end su `sandbox/` con backend `claude`, come da
   `docs/SPEC.md` (`tools/launch.ps1`, decisione `ask_user`, Task Wall, monitor, merge in gioco).
2. Ripetere lo stesso flusso con backend `sim` per la QA deterministica degli screenshot.
3. Verificare ripristino stato dopo riavvio a metà esecuzione e riconnessione dopo kill del Foreman.
4. Estendere i test JUnit del mod oltre il controllo di salute degli snapshot, cercando regressioni
   nel parsing del protocollo e nella validazione dei messaggi in ingresso.
5. Collegare i problemi segnalati da `Protocol.Snapshot.validate()` a un indicatore in mondo
   (per esempio il banner di connessione) invece che al solo log.
6. Aggiornare `docs/QA.md` con l'esito della barra visiva e dei test end-to-end.

## Non previsto

- Push automatici o merge senza conferma dell'utente.
- Modifiche al checkout dell'utente o ai suoi branch.
